#!/usr/bin/env bash
# Fail when the Spring Test Profiler reports more Spring contexts than allowed.
#
# Usage: scripts/check-context-count.sh [maxContexts] [resultsFile]
# Example: scripts/check-context-count.sh 9 target/spring-test-profiler/results.json
set -euo pipefail

maxContexts="${1:-9}"
resultsFile="${2:-target/spring-test-profiler/results.json}"

if ! command -v jq >/dev/null 2>&1; then
  echo "jq is required but not installed"
  exit 2
fi

if [ ! -f "$resultsFile" ]; then
  echo "Profiler results not found at $resultsFile. Did the tests run with the Spring Test Profiler enabled?"
  exit 2
fi

contextsCreated=$(jq -r '.contextsCreated' "$resultsFile")
cacheHitRatio=$(jq -r '.contextCacheHitRatio' "$resultsFile")
contextCreationTimeMs=$(jq -r '.totalContextCreationTimeMs' "$resultsFile")

summary="Spring contexts created: ${contextsCreated} (limit ${maxContexts}), cache hit ratio: ${cacheHitRatio}, time spent creating contexts: ${contextCreationTimeMs} ms"
echo "$summary"

if [ -n "${GITHUB_STEP_SUMMARY:-}" ]; then
  {
    echo "### Spring Test Profiler"
    echo ""
    echo "| Metric | Value |"
    echo "|---|---|"
    echo "| Contexts created | ${contextsCreated} |"
    echo "| Limit | ${maxContexts} |"
    echo "| Cache hit ratio | ${cacheHitRatio} |"
    echo "| Context creation time | ${contextCreationTimeMs} ms |"
  } >> "$GITHUB_STEP_SUMMARY"
fi

if [ "$contextsCreated" -gt "$maxContexts" ]; then
  echo "::error::The test suite created ${contextsCreated} Spring contexts, the limit is ${maxContexts}"
  exit 1
fi

echo "Context count is within the limit"
