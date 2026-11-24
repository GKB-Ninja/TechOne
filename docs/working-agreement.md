# Working agreement

## Our board

Columns: Backlog, Ready, In Progress, Review, Done.

A card is one piece of work that one person can finish in 1-2 days. If it's bigger than that, we split it.

## When a card can move

- **Backlog:** anyone can add a card here. Cards are ordered by priority, the top one comes first.
- **Ready:** the card has an ID and acceptance criteria, and every card it's blocked by is Done. We choose what goes into Ready together at our weekly check. If Ready gets empty during the week, anyone can move the top card from Backlog to Ready, as long as its blocking cards are Done, and tells the team in our group chat.
- **In Progress:** you only pull cards from Ready, never from Backlog. Assign yourself and set the Start date. For code cards, open a branch named after the card ID and write the failing test first. Document cards don't need a test.
- **Review:** the PR is open, CI is green and the card is waiting for another member's review.
- **Done:** everything in the Definition of Done is true. Set the Done date.

## WIP limits

Ready 5, In Progress 3, Review 2. One card per person in In Progress.

If a column is full, don't pull a new card. Help finish what's already there first, usually by reviewing.

If we ever go over a limit, we write it in the weekly notes with the reason.

## Blocked cards

Add the `blocked` label and a comment saying what you're waiting for. The card stays in its column. Blocked cards are the first thing we look at in the weekly check.

## Definition of Done

- all acceptance criteria are checked
- tests pass, for code cards
- someone else reviewed and approved the PR
- CI is green on main after the merge
- docs are updated if the change affects them, like the SRS or README
- no known critical bug is left

## Flow metrics

- **Cycle time:** from the day a card enters In Progress to the day it enters Done
- **Throughput:** how many cards reached Done in a week
- **Card age:** how many days a card has been sitting in In Progress or Review
- **WIP:** how many cards are in In Progress and Review

We write these down every week in `docs/weekly-notes.md`.

## Weekly check

Every Tuesday, 45 minutes, online is fine. We look at the numbers, pick one problem, decide what to change, and check whether last week's change worked.
