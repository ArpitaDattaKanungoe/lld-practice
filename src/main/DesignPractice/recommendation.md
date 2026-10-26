Approach A: Score every video whenever a user visits
1.        User opens YouTube -> Check all 1 billion videos -> ML model scores every video -> Sort scores and return the top 10

Why it fails: The system must evaluate an enormous number of videos for every request. Even if scoring one video is fast, doing it a billion times is too expensive and slow.

Approach B:  Precompute every user's recommendations
1.        Calculate recommendations in advance  -> Store recommendations for every user  ->Return the stored list when the user visits

Why it fails: There are billions of users and billions of videos. Storing and maintaining complete recommendation lists for every user becomes enormously expensive. New users and new videos also make the problem harder.

<img width="2096" height="1778" alt="mermaid-diagram (7)" src="https://github.com/user-attachments/assets/923aec94-3751-441a-a133-704b1be28eb2" />
<img width="3422" height="2288" alt="mermaid-diagram (8)" src="https://github.com/user-attachments/assets/44ea5ba1-6423-42b1-bec9-be698cdef82e" />
