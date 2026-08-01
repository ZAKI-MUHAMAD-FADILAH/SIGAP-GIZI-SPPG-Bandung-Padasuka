#!/bin/bash

# Batas maksimum baris per berkas
MAX_LINES=200

# Direktori yang dipindai
SCAN_DIRS=("android" "supabase" "google-apps-script" "scripts")

# Pola berkas yang dipindai
FILE_PATTERNS=("*.kt" "*.kts" "*.java" "*.ts" "*.tsx" "*.js" "*.sql" "*.gs")

# Pengecualian
EXCLUDE_PATTERNS=("*/build/*" "*/generated/*" "*/node_modules/*" "*/vendor/*" "*/.gradle/*" "*/gradle-wrapper.jar")

VIOLATIONS=0

echo "Memeriksa batas baris kode (Maks: $MAX_LINES baris)..."

for dir in "${SCAN_DIRS[@]}"; do
    if [ -d "$dir" ]; then
        for pattern in "${FILE_PATTERNS[@]}"; do
            find "$dir" -name "$pattern" | while read -r file; do
                # Periksa apakah berkas dikecualikan
                skip=0
                for exclude in "${EXCLUDE_PATTERNS[@]}"; do
                    if [[ $file == $exclude ]]; then
                        skip=1
                        break
                    fi
                done

                if [ $skip -eq 0 ]; then
                    line_count=$(wc -l < "$file")
                    if [ "$line_count" -gt "$MAX_LINES" ]; then
                        echo "[Gagal] $file: $line_count baris"
                        VIOLATIONS=$((VIOLATIONS + 1))
                    fi
                fi
            done
        done
    fi
done

if [ $VIOLATIONS -gt 0 ]; then
    echo "Ditemukan $VIOLATIONS pelanggaran batas baris kode."
    exit 1
else
    echo "Semua berkas memenuhi batas baris kode."
    exit 0
fi
