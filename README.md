# TechOne
Lightweight tech retailer platform with strict stock management, category-specific return rules, staff authorization pipelines, and simulated fulfillment.

## Tech stack

| Part | Choice |
|---|---|
| Language | Java 21 |
| Build | Maven (3.9 or newer) |
| Tests | JUnit 5 |
| Version control | Git / GitHub |
| Continuous integration | GitHub Actions |

## Build and run the tests

Requirements: JDK 21 and Maven 3.9 or newer.

```
mvn verify
```

This compiles the code and runs every test. CI runs exactly the same command on every pull request and on every push to `main`.

## Continuous integration

Every pull request is built and tested automatically. A pull request can only be merged into `main` when the **Build and test** check is green and another team member has approved it. How the pipeline works and how to read a failed run are described in [docs/ci.md](docs/ci.md).

## Project documents

- [Working agreement](docs/working-agreement.md): board rules, WIP limits, Definition of Done
- [Dependency map](docs/dependency-map.md)
- [Weekly notes](docs/weekly-notes.md)
- [AI usage log](docs/ai-usage.md)
- [Continuous integration](docs/ci.md)
