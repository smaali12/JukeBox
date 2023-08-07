create database jukeBox;
use jukeBox;
create table Songs(
song_id int auto_increment primary key,
song_name varchar(35)unique not null,
album_name varchar(20)not null,
artist_name varchar(30)not null,
geners varchar(20)not null,
song_duration varchar(15)not null
);
desc Songs;
insert into Songs(song_name,album_name,artist_name,geners,song_duration) values('Pal','Jalebi','Arjit Singh,Shrey ','Love','4:08');
insert into Songs(song_name,album_name,artist_name,geners,song_duration) values('Tere Bin Nahin Lagda','Sultan E Sufi','Nusrat Fateh Ali Khan','Gazels','5:47');
insert into Songs(song_name,album_name,artist_name,geners,song_duration) values('Twinkle Twinkle Little Star','Nursery Rhymes','Jane Taylor','Kids Songs','2:45');
insert into Songs(song_name,album_name,artist_name,geners,song_duration) values('Achutam keshwam','God','Anuradha Paudwal','Devotional Songs','4:23');
insert into Songs(song_name,album_name,artist_name,geners,song_duration) values('Sanu Ek Pal Chain Na','Immortal Collection','Nusrat Fateh Ali Khan','Sufi Songs','12:46');
select* from Songs;


