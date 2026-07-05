Redis solves concurrency, not persistence.

The database solves durability and business state ( seat held, reserved .. ) , then we use cronjob to make seats available if not booked

<img width="1600" height="1200" alt="yo" src="https://github.com/user-attachments/assets/4b58fa7a-d0bc-4a8b-adc0-f8c4b81e2bf0" />
