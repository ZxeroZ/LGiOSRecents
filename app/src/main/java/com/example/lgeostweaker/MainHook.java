package com.example.lgeostweaker;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MainHook implements IXposedHookLoadPackage {

    private static final float STACK_GAP = 90f;

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.lge.launcher3") && !lpparam.packageName.equals("com.android.launcher3")) {
            return;
        }

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.TaskView", "setFullscreenProgress", float.class, new XC_MethodHook() {
            @Override protected void beforeHookedMethod(MethodHookParam param) { param.args[0] = 0.0f; }
        });

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.TaskView", "setDimAlpha", float.class, new XC_MethodHook() {
            @Override protected void beforeHookedMethod(MethodHookParam param) { param.args[0] = 0.0f; }
        });

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.TaskView", "onFinishInflate", new XC_MethodHook() {
            @Override protected void afterHookedMethod(MethodHookParam param) { ((View) param.thisObject).setElevation(0.0f); }
        });

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.TaskView", "onTaskListVisibilityChanged", boolean.class, new XC_MethodHook() {
            @Override protected void beforeHookedMethod(MethodHookParam param) { param.args[0] = true; }
        });

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.RecentsView", "updateStackLayout", new XC_MethodHook() {
            @Override protected void beforeHookedMethod(MethodHookParam param) { param.setResult(null); }
        });

        XC_MethodHook scrollHook = new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                ViewGroup rv = (ViewGroup) param.thisObject;
                int count = rv.getChildCount();
                int scrollX = rv.getScrollX();
                float width = rv.getWidth();

                if (count == 0 || width == 0) return;

                float nativeSpacing = width;
                View firstTask = null;
                View secondTask = null;
                for (int i = 0; i < count; i++) {
                    View v = rv.getChildAt(i);
                    if (v != null && v.getClass().getSimpleName().equals("TaskView")) {
                        if (firstTask == null) firstTask = v;
                        else if (secondTask == null) {
                            secondTask = v;
                            break;
                        }
                    }
                }
                if (firstTask != null && secondTask != null) {
                    nativeSpacing = Math.abs(secondTask.getLeft() - firstTask.getLeft());
                }
                if (nativeSpacing <= 0) nativeSpacing = width;

                float screenCenter = scrollX + (width / 2f);

                for (int i = 0; i < count; i++) {
                    View child = rv.getChildAt(i);
                    if (child == null || !child.getClass().getSimpleName().equals("TaskView")) continue;

                    float childCenter = child.getLeft() + (child.getWidth() / 2f);
                    float distanceToCenter = childCenter - screenCenter;
                    float progress = distanceToCenter / nativeSpacing;

                    if (distanceToCenter < 0) {
                        float targetScreenPos = progress * STACK_GAP;
                        child.setTranslationX(targetScreenPos - distanceToCenter);
                    } else {
                        child.setTranslationX(0f);
                    }

                    child.setScaleX(1.0f);
                    child.setScaleY(1.0f);
                    child.setTranslationZ(child.getLeft() * 0.01f);

                    try {
                        ViewGroup headerView = (ViewGroup) XposedHelpers.getObjectField(child, "mHeaderView");
                        if (headerView != null) {
                            float titleAlpha = Math.max(0.0f, 1.0f - (Math.abs(progress) * 4.0f));
                            for (int j = 0; j < headerView.getChildCount(); j++) {
                                View hChild = headerView.getChildAt(j);
                                if (hChild instanceof TextView) {
                                    hChild.setAlpha(titleAlpha);
                                }
                            }
                        }
                    } catch (Throwable t) {}
                }
            }
        };

        hookSilently(lpparam.classLoader, "com.android.quickstep.views.RecentsView", "updateStackProperties", scrollHook);
        hookSilently(lpparam.classLoader, "com.android.quickstep.views.RecentsView", "updateCurveProperties", scrollHook);
        hookSilently(lpparam.classLoader, "com.android.quickstep.views.RecentsView", "updateCurveProperties", boolean.class, scrollHook);
    }

    private void hookSilently(ClassLoader classLoader, String className, String methodName, Object... params) {
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, params);
        } catch (Throwable t) {}
    }
}