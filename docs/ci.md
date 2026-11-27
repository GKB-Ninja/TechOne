# Continuous integration (CI)

Card: **SETUP-02 — CI pipeline: build + test on every PR**

## What CI does for us, in one paragraph

Every time someone opens a pull request (PR), pushes new commits to a PR, or something is merged into `main`, GitHub starts a fresh Linux machine, downloads our code, builds it with Maven and runs every JUnit test. If anything fails, the PR gets a red ✗ and cannot be merged. If everything passes, it gets a green ✓. Nobody has to remember to run the tests, and `main` stays working.

## Where things are

| What | Where |
|---|---|
| Pipeline definition ("pipeline as code") | `.github/workflows/ci.yml` |
| Build definition | `pom.xml` |
| Run history | GitHub → **Actions** tab → workflow **CI** |
| Result on a PR | bottom of the PR page ("Checks") and the PR's **Checks** tab |
| Test results per run | the **Test results** check, plus the run's **Summary** page |
| Raw test reports (XML) | the run's **Summary** page → *Artifacts* → `test-reports-<commit>` (kept 30 days) |

## When it runs (SETUP-02.AC2)

| Event | Runs? |
|---|---|
| A PR is opened, or new commits are pushed to a PR branch | yes |
| A push (merge) to `main` | yes |
| Someone clicks **Run workflow** in the Actions tab | yes |
| A push to a feature branch with no PR | no — open a PR (a draft PR is fine) to get CI |

If you push twice quickly to the same PR, the older run is cancelled and only the newest commit is checked. Runs on `main` are never cancelled, so every merge keeps its own result.

## What it does (SETUP-02.AC3, AC4, AC5)

The single job is called **Build and test**. Its steps are:

1. **Check out the code** — the exact commit being checked.
2. **Set up Java 21** — Eclipse Temurin JDK 21. Downloaded Maven dependencies are cached between runs to make builds faster.
3. **Build and run all tests** — `mvn --batch-mode --no-transfer-progress verify`. This compiles the code, runs every test under `src/test/java`, and packages the jar. Any compile error or failing test makes this step, and therefore the job, fail. The build also fails if no tests are found, so a green run always means tests actually ran.
4. **Publish test results** — runs even if step 3 failed. Adds a **Test results** check listing every test (passed and failed), with the failure message shown next to the failing test.
5. **Upload raw test reports** — runs even if step 3 failed. Saves the Surefire XML reports as a downloadable artifact named after the commit hash, so a result can always be tied to an exact source revision.

## How a failing test blocks a merge (SETUP-02.AC5, AC6)

The `main` branch has a ruleset that requires the **Build and test** check to pass and one approval from another team member before a PR can be merged. Nobody can bypass it. On a PR, the check is marked **Required**. Anyone with read access can see the active rules on the repository's **Rules** page (`<repo URL>/rules`).

## Run the same check on your own computer

You need **JDK 21** and **Maven 3.9 or newer**.

```
mvn verify
```

If this passes locally, CI should pass too. If CI fails but your machine passes, read the CI log first: the difference is usually a file that wasn't committed, or a test that depends on your machine (time zone, file paths, ordering).

## Reading a failed run

1. On the PR, click **Details** next to the red ✗ check.
2. Open the **Test results** check to see which test failed and why.
3. If no tests are listed, the build failed before testing: open the **Build and run all tests** step log and look for `[ERROR]` lines (usually a compile error).
4. Fix it on the same branch and push. CI runs again automatically.

## Controlled failure exercise (evidence for AC4 and AC5)

The assignment asks us to show at least one failed pipeline, its cause and the fix, and to label a deliberate one as a controlled exercise. We do this once, after `main` requires the **Build and test** check:

1. Create a branch `SETUP-02-controlled-failure` from `main`.
2. In `BuildSetupTest`, temporarily change `feature >= 21` to `feature >= 99`. Commit with the message `SETUP-02: controlled failure exercise - deliberately failing test`.
3. Open a PR titled `SETUP-02: controlled failure exercise (do not merge)`.
4. Check: the **Build and test** check is red, the **Test results** check names `testsRunOnJava21OrNewer` with its failure message (AC4), and the **Merge** button is blocked (AC5).
5. Revert the change, push, and check that everything turns green.
6. Close the PR **without merging**. Keep the branch, so the failing revision stays available as evidence.
7. Record the run links in the table below.

This exercise never touches `main`, so `main` is never broken.

## CI evidence log

Record notable runs here: the controlled exercise, and every genuine failure on a PR or on `main` (cause and fix). This is what the instructor looks at for the "failure diagnosis" part of the CI grade.

| Date | PR / run link | Commit | Result | Genuine or controlled | Cause | Fix / follow-up |
|---|---|---|---|---|---|---|
| | | | | | | |

## Not in this pipeline yet

SETUP-02 covers build and test only. The assignment (section 8) also expects these. They aren't on any card yet:

- packaging a versioned artifact or Docker image, tied to the source revision
- deploying a successful build to a test environment, stopped automatically if build or tests fail
- a coverage report for the core business logic (target: at least 80 % line coverage)
- release tags linked to completed work and CI runs

When they are added, they go into `.github/workflows/ci.yml` as later steps or jobs that only run after **Build and test** succeeds.
