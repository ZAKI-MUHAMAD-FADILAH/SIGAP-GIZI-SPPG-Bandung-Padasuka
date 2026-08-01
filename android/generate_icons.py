import os
from PIL import Image

icon_path = "../ICON-APK.png"
res_path = "app/src/main/res"

# Legacy sizes
sizes = {
    "mipmap-mdpi": 48,
    "mipmap-hdpi": 72,
    "mipmap-xhdpi": 96,
    "mipmap-xxhdpi": 144,
    "mipmap-xxxhdpi": 192
}

# Adaptive sizes (108dp)
adaptive_sizes = {
    "mipmap-mdpi": 108,
    "mipmap-hdpi": 162,
    "mipmap-xhdpi": 216,
    "mipmap-xxhdpi": 324,
    "mipmap-xxxhdpi": 432
}

if not os.path.exists(icon_path):
    print(f"Icon not found at {icon_path}")
    exit(1)

img = Image.open(icon_path).convert("RGBA")

for folder, size in sizes.items():
    target_dir = os.path.join(res_path, folder)
    if not os.path.exists(target_dir):
        os.makedirs(target_dir)

    resized_img = img.resize((size, size), Image.Resampling.LANCZOS)
    resized_img.save(os.path.join(target_dir, "ic_launcher.png"))
    resized_img.save(os.path.join(target_dir, "ic_launcher_round.png"))
    print(f"Generated legacy icons for {folder} ({size}x{size})")

for folder, size in adaptive_sizes.items():
    target_dir = os.path.join(res_path, folder)
    if not os.path.exists(target_dir):
        os.makedirs(target_dir)

    # Adaptive foreground: place original icon in center of 108dp canvas
    # We scale the icon to 72% of the canvas size to stay in the safe zone
    icon_scale = 0.72
    icon_size = int(size * icon_scale)
    fg_icon = img.resize((icon_size, icon_size), Image.Resampling.LANCZOS)

    canvas = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    offset = (size - icon_size) // 2
    canvas.paste(fg_icon, (offset, offset), fg_icon)

    canvas.save(os.path.join(target_dir, "ic_launcher_foreground.png"))
    print(f"Generated adaptive foreground for {folder} ({size}x{size})")
