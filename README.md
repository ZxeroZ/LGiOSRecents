# 🌟 LGeOS Recents 🌟

Modifies the LG Launcher's recent apps view to feature an iOS/HyperOS-style stacked cards layout.

## ✨ Features
- **Stacked Layout**  
  Apps are displayed in a compact, overlapping stack rather than the standard LG side-by-side view.
- **Minimalist UI**  
  Automatically hides app titles for a cleaner look and centers icons.
- **Memory Fix**  
  Prevents the launcher from unloading task thumbnails to avoid "grey card" glitches.
- **Smooth Animations**  
  Optimized scaling and translation math for fluid scrolling.

## ⚠️ Compatibility & Warnings

> **WARNING**  
> This module is exclusively tested on the LG V60 ThinQ running the latest available Android version.

> **No guarantee for other models**  
> I cannot guarantee stability on other LG devices or different Android versions. Use it at your own risk.

> **System Stability**  
> This module hooks directly into `com.lge.launcher3`. If you encounter a bootloop or launcher crash, disable the module   immediately.

> **Testing**  
> If you decide to port this to another device, be prepared to adjust the `STACK_GAP` constant in `MainHook.java` to match your specific screen density.

## 📥 Installation

1. Install the latest version of LSPosed.  
2. Install the module.  
3. Enable the module in the LSPosed manager and select `com.lge.launcher3`and System UI as the target scope.  
4. Reboot your device.

## 🔧 Troubleshooting

> **Cards look misaligned**  
> Adjust the `STACK_GAP` value in the source code to fit your screen resolution.

> **Titles still showing**  
> The module force-hides TextView elements in the `mHeaderView`. If your specific firmware uses a different header structure, you may need to inspect the class names in your launcher3 APK.
