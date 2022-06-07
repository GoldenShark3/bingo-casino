create table bets
(
    id UUID primary key,
    previous_bet_id UUID not null,
    created_at  timestamp not null,
    user_id     bigint    not null,
    money_delta decimal    not null
);