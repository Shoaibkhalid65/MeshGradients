# 🌈 Mesh Gradients in Jetpack Compose

Mesh Gradients are one of the most **visually captivating modern design trends**, often used in UI/UX design to create vibrant, fluid, and organic color backgrounds. Unlike traditional linear or radial gradients, mesh gradients allow **multiple colors to blend across different control points**, giving a smooth, natural, and abstract look.

This repository demonstrates **Mesh Gradients in Jetpack Compose**, exploring their use with **custom shapes, custom UI components, and Canvas API**.

---

## 📹 Demo Video

👉 [Watch the Mesh Gradients Demo on YouTube](https://youtu.be/C7iq8m2dQOo?si=n-EicltONHEyz0hD)

---

## ✨ What’s Inside

* ✅ Mesh Gradients implementation in **Jetpack Compose**.
* ✅ Using **@sinasamaki’s Mesh Gradient Modifier** ([article](https://www.sinasamaki.com/mesh-gradients-in-jetpack-compose/), [video](https://youtu.be/C7iq8m2dQOo?si=n-EicltONHEyz0hD)).
* ✅ Demonstrated gradients on **custom UI components** (Cards, Buttons, etc.).
* ✅ Built **responsive custom shapes** with the Canvas API.
* ✅ Added a **helper class for CouponShape** (with arc cutouts).

---

## 📂 Code Locations

* 🔘 **Button Mesh Gradient** → `practice/Sample6.kt`
* 🃏 **Cards Mesh Gradient** → `CardsScreen.kt`
* ✂ **CouponShape Helper Class** → `customShapes/CouponShape.kt`

---

## 🧩 CouponShape Helper Class

The **CouponShape** is a reusable shape with arc cutouts on the horizontal sides.
It’s fully **responsive**, customizable, and comes with two public constructors:

1. **By radius height** → Takes radius height and generates arcs automatically.
2. **By arc count & space height** → Takes number of arcs + space height, and auto-calculates arcs.

📌 Code: [`customShapes/CouponShape.kt`](customShapes/CouponShape.kt)

---

## 🔗 Useful References

### Official Mesh Gradient Work by @sinasamaki

* 📄 [Article: Mesh Gradients in Jetpack Compose](https://www.sinasamaki.com/mesh-gradients-in-jetpack-compose/)
* 📹 [YouTube Video by @sinasamaki](https://youtu.be/C7iq8m2dQOo?si=n-EicltONHEyz0hD)

---

### Third-Party Library (Not Used in This Repo)

While this project **only uses @sinasamaki’s modifier**, there is also a 3rd-party Mesh Gradient library for Compose:

* 📄 [Medium Article](https://proandroiddev.com/compose-mesh-gradient-apple-swiftui-like-mesh-gradients-for-jetpack-compose-a9f48177c4f7)
* 💻 [GitHub Repo](https://github.com/om252345/composemeshgradient)

---

### 🎨 Color & Gradient Resources

* 🌐 [Coolors Gradient Generator](https://coolors.co/gradients)
* 🌐 [UI Gradients](https://uigradients.com/)
* 🌐 [Mesh Gradient Creator](https://meshgradient.com/)
* 🌐 [Coolors Image Palette Picker](https://coolors.co/image-picker)
* 🌐 [MyColorSpace Gradient Generator](https://mycolor.space/?hex=%23845EC2&sub=1)

---

## 🎥 Demo Preview

*(You can upload your demo video/gif here, e.g. `/preview/demo.mp4` or a gif)*

---

## ⭐ Support

If you find this repository helpful, **consider giving it a star ⭐** — it motivates me to create and share more awesome Compose experiments!

---

