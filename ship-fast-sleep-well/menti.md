1. "How confident are you to deploy to production on a Friday at 4 PM?"
    - Type: scale from 1 to 5.
    - It hooks the audience and measures the main point of the talk. You can show the result again on the last slide.
2. "How long does it take from commit to production?"
    - Type: single choice. The answers are under 15 min, under 1 hour, under 1 day, under 1 week, longer.
    - It links to the Build and Verify parts (tests and CI/CD).
3. "The pager rings at 3 AM. What do you have?"
    - Type: multiple choice. The answers are:
        - Feature flag or kill switch
        - Alerts with a runbook
        - Logs I can search by user
        - Distributed traces
        - Canary tests
        - Only experience of the team
    - It links to the Ship and Operate parts. It also covers the old question "How do you stop a broken feature?", because the feature flag is one of the answers.
