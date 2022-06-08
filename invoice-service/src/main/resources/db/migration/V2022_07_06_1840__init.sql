create table bets
(
    id UUID primary key,
    previous_bet_id UUID null,
    created_at  timestamp not null,
    user_id     bigint    not null,
    money_delta decimal(10, 2)    not null
);