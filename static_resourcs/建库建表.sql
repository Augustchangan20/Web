create database song_dictionary charset utf8mb4;
use song_dictionary;
create table dictionary(
  english varchar(100) not null unique,
  chinese varchar(100) not null
);
insert into dictionary values
     ('apple','苹果'),
     ('pear','梨'),
     ('watermelon','西瓜'),
     ('pineapple','菠萝');