#!/usr/bin/env bash
# Clone the PetClinic demo app (pinned commit) via the course repo setup script.
# Usage: ./demo/setup.sh
set -euo pipefail

COURSE_DIR="${COURSE_DIR:-$HOME/Development/git/agentic-testing-for-spring-boot-course}"
TARGET="$(cd "$(dirname "$0")" && pwd)/spring-petclinic"
REPO="https://github.com/spring-projects/spring-petclinic.git"
COMMIT="818c4136ea971c21674525f9053de0d9c7ad8cfe"

if [ -d "$TARGET/.git" ]; then
  git -C "$TARGET" fetch --quiet origin "$COMMIT"
  git -C "$TARGET" checkout --quiet --force "$COMMIT"
else
  git clone --quiet "$REPO" "$TARGET"
  git -C "$TARGET" checkout --quiet "$COMMIT"
fi
echo "PetClinic pinned at $(git -C "$TARGET" rev-parse --short HEAD)"

# Install the skill library into the demo project so Claude Code can load it
if [ -d "$COURSE_DIR/spring-boot-testing-skills" ]; then
  mkdir -p "$TARGET/.claude/skills"
  rm -rf "$TARGET/.claude/skills/spring-boot-testing"
  cp -R "$COURSE_DIR/spring-boot-testing-skills" "$TARGET/.claude/skills/spring-boot-testing"
  echo "Skills copied to $TARGET/.claude/skills/spring-boot-testing"
else
  echo "Course repo not found at $COURSE_DIR - skills not installed (set COURSE_DIR)"
fi
echo "Known issue: PostgresIntegrationTests needs port 5432 free. Green run: ./mvnw test -Dtest='!PostgresIntegrationTests'"
