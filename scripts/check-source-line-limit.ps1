# Batas maksimum baris per berkas
$MAX_LINES = 200

# Direktori yang dipindai
$SCAN_DIRS = @("android", "supabase", "google-apps-script", "scripts")

# Pola berkas yang dipindai
$FILE_PATTERNS = @("*.kt", "*.kts", "*.java", "*.ts", "*.tsx", "*.js", "*.sql", "*.gs")

# Pengecualian (Regex)
$EXCLUDE_REGEX = "(\\build\\|\\generated\\|\\node_modules\\|\\vendor\\|\\.gradle\\|gradle-wrapper\.jar)"

$Violations = 0

Write-Host "Memeriksa batas baris kode (Maks: $MAX_LINES baris)..." -ForegroundColor Cyan

foreach ($dir in $SCAN_DIRS) {
    if (Test-Path $dir) {
        foreach ($pattern in $FILE_PATTERNS) {
            $files = Get-ChildItem -Path $dir -Filter $pattern -Recurse
            foreach ($file in $files) {
                # Periksa apakah berkas dikecualikan
                if ($file.FullName -notmatch $EXCLUDE_REGEX) {
                    $lineCount = (Get-Content $file.FullName).Count
                    if ($lineCount -gt $MAX_LINES) {
                        Write-Host "[Gagal] $($file.FullName): $lineCount baris" -ForegroundColor Red
                        $Violations++
                    }
                }
            }
        }
    }
}

if ($Violations -gt 0) {
    Write-Host "Ditemukan $Violations pelanggaran batas baris kode." -ForegroundColor Red
    exit 1
} else {
    Write-Host "Semua berkas memenuhi batas baris kode." -ForegroundColor Green
    exit 0
}
