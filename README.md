&lt;div align="center"&gt;

# 🌟 LGiOS Recents

**Transforms the LG Launcher's recent apps view** into an iOS/HyperOS-style stacked cards layout.

[![Version](https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge&logo=github)](https://github.com/yourusername/LGeOS-Recents/releases)
[![Android](https://img.shields.io/badge/Android-10+-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://www.android.com/)
[![LSPosed](https://img.shields.io/badge/LSPosed-Required-FF6D00?style=for-the-badge&logo=android&logoColor=white)](https://github.com/LSPosed/LSPosed)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

&lt;/div&gt;

---


## ✨ Features

| Feature | Description |
|---|---|
| **🗂️ Stacked Layout** | Apps displayed in a compact, overlapping stack instead of LG's standard side-by-side view. |
| **🎨 Minimalist UI** | Automatically hides app titles for a cleaner look . |
| **🧠 Memory Fix** | Prevents the launcher from unloading task thumbnails to avoid "grey card" glitches. |
| **🚀 Smooth Animations** | Optimized scaling and translation math for fluid scrolling. |

---

## ⚠️ Compatibility & Warnings

&gt; [!WARNING]
&gt; **This module is exclusively tested on the LG V60 ThinQ** running the latest available Android version. **No guarantee for other models** or Android versions. Use it at your own risk.

&gt; [!CAUTION]
&gt; **System Stability:** This module hooks directly into `com.lge.launcher3`. If you encounter a bootloop or launcher crash, **disable the module immediately** via Safe Mode or ADB.

&gt; [!TIP]
&gt; **Porting to other devices:** If you decide to port this module to another device, be prepared to adjust the `STACK_GAP` constant in `MainHook.java` to match your specific screen density.

---

## 📥 Installation

1. Install the latest version of **LSPosed**.
2. Install this module (APK).
3. Open the LSPosed manager, enable the module, and select:
   - `com.lge.launcher3`
   - **System UI** (as additional scope)
4. **Reboot** your device.

---

## 🔧 Troubleshooting

| Issue | Solution |
|---|---|
| **Cards look misaligned** | Adjust the `STACK_GAP` value in the source code to fit your screen resolution. |
| **Titles still showing** | The module force-hides `TextView` elements in `mHeaderView`. If your specific firmware uses a different header structure, you may need to inspect the class names in your launcher3 APK. |

---

## 🛠️ Tech Stack

&lt;p align="center"&gt;
  &lt;img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"&gt;
  &lt;img src="https://img.shields.io/badge/Xposed%20Framework-FF6D00?style=for-the-badge&logo=android&logoColor=white" alt="Xposed"&gt;
  &lt;img src="https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" alt="Android Studio"&gt;
&lt;/p&gt;

---

## 🤝 Contributing

Contributions are welcome! If you port the module to another LG device, feel free to open a PR and share your `STACK_GAP` configuration.

---

&lt;div align="center"&gt;

**Made with ❤️ for the LG community**

&lt;/div&gt;
