# PowerShell version of the Makefile update-version task

$VERSION_FILE = "VERSION"
$BUILD_GRADLE = "build.gradle"
$CHANGELOG = "CHANGELOG.md"
$DATE = Get-Date -Format "yyyy-MM-dd"

$VERSION = Get-Content $VERSION_FILE -Raw
$GRADLE_VERSION = "$VERSION-SNAPSHOT"

Write-Host "Updating build.gradle to version: $GRADLE_VERSION"

# Update build.gradle
$gradleContent = Get-Content $BUILD_GRADLE -Raw
if ($gradleContent -match "^version\s*=") {
    $gradleContent = $gradleContent -replace "^version\s*=.*", "version = '$GRADLE_VERSION'"
} else {
    Add-Content -Path $BUILD_GRADLE -Value "version = '$GRADLE_VERSION'"
}
Set-Content -Path $BUILD_GRADLE -Value $gradleContent
Write-Host "build.gradle updated successfully."
Write-Host ""

Write-Host "Checking CHANGELOG.md for version $VERSION..."

$changelogContent = Get-Content $CHANGELOG -Raw

if ($changelogContent -match "## \[$VERSION\]") {
    Write-Host "✅ Version $VERSION already exists in CHANGELOG.md"
    Write-Host "🚨 NOTE: YOU WILL NEED TO ADD YOUR CHANGES TO THE CHANGELOG BEFORE PUSHING 🚨"
    exit 0
}

Write-Host "Adding version $VERSION to CHANGELOG.md after line 7"

# Build new section
$NEW_SECTION = @"
## [$VERSION] - $DATE --DRAFT--

### Added
- 
### Changed
- 
### Fixed
- 

"@

# Read first 7 lines and the rest
$head = (Get-Content $CHANGELOG)[0..6] -join "`n"
$tail = (Get-Content $CHANGELOG)[7..((Get-Content $CHANGELOG).Length - 1)] -join "`n"

# Rebuild file
$newChangelog = "$head`n$NEW_SECTION`n$tail`n"
Set-Content -Path $CHANGELOG -Value $newChangelog

Write-Host "✅ Version $VERSION section added to CHANGELOG.md"
Write-Host "🚨 NOTE: YOU WILL NEED TO ADD YOUR CHANGES TO THE CHANGELOG BEFORE PUSHING 🚨"
