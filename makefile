# Makefile

VERSION_FILE := VERSION
BUILD_GRADLE := build.gradle
CHANGELOG := CHANGELOG.md
DATE := $(shell date +%Y-%m-%d)

VERSION := $(shell cat $(VERSION_FILE))
GRADLE_VERSION := $(VERSION)-SNAPSHOT

# Detect platform for sed
SED_I := sed -i
UNAME_S := $(shell uname)
ifeq ($(UNAME_S),Darwin)
    SED_I := sed -i ''  # macOS
endif

.PHONY: update-version

update-version:
	@echo "Updating build.gradle to version: $(GRADLE_VERSION)"; \
	if grep -q "^version = " $(BUILD_GRADLE); then \
		$(SED_I) "s/^version = .*/version = '$(GRADLE_VERSION)'/" $(BUILD_GRADLE); \
	else \
		echo "version = '$(GRADLE_VERSION)'" >> $(BUILD_GRADLE); \
	fi; \
	echo "build.gradle updated successfully."; \
	\
	echo "Checking CHANGELOG.md for version $(VERSION)..."; \
	if grep -q "^## \[$(VERSION)\]" $(CHANGELOG); then \
		echo "✅ Version $(VERSION) already exists in CHANGELOG.md"; \
		echo "🚨 NOTE: YOU WILL NEED TO ADD YOUR CHANGES TO THE CHANGELOG BEFORE PUSHING 🚨"; \
	else \
		echo "Adding version $(VERSION) to CHANGELOG.md after line 7"; \
		NEW_SECTION=$$(printf "## [$(VERSION)] - $(DATE) --DRAFT--\n\n### Added\n- \n### Changed\n- \n### Fixed\n- \n"); \
		HEAD=$$(head -n 7 $(CHANGELOG)); \
		TAIL=$$(tail -n +8 $(CHANGELOG)); \
		printf "%s\n%s\n\n%s\n" "$$HEAD" "$$NEW_SECTION" "$$TAIL" > $(CHANGELOG); \
		echo "✅ Version $(VERSION) section added to CHANGELOG.md"; \
		echo "🚨 NOTE: YOU WILL NEED TO ADD YOUR CHANGES TO THE CHANGELOG BEFORE PUSHING 🚨"; \
	fi



