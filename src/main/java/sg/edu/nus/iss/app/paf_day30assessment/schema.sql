create DATABASE if not exists acme_bank;
use acme_bank;
create Table accounts(
    account_id nvarchar(20) not null,
    name nvarchar(100) not null,
    balance decimal(10, 2) not null,
    constraint pk_account_id primary key (account_id)
);
insert into accounts(account_id, name, balance)
values(V9L3Jd1BBI, fred, 100.00);
insert into accounts(account_id, name, balance)
values(fhRq46Y6vB, barney, 300.00);
insert into accounts(account_id, name, balance)
values(uFSFRqUpJy, wilma, 1000.00);
insert into accounts(account_id, name, balance)
values(ckTV56axff, betty, 1000.00);
insert into accounts(account_id, name, balance)
values(gcnwbshbh, pebbles, 50.00);
insert into accounts(account_id, name, balance)
values(if9l185l18, bambam, 50.00);
--bulk insert 
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/data.csv' INTO TABLE accounts FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 ROWS;