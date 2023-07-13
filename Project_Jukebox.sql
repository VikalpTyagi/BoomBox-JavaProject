create database jukebox;
use jukebox;
create table songs(
songID int auto_increment primary key,
songName varchar(30) unique not null,
artist_name varchar(58),
genre varchar(20),
album varchar(25),
duration varchar(6) not null);

insert into songs(songName,artist_name,genre,album,duration) values('Work hard play hard','Wiz Khalifa','Pop','O.N.I.F.C','3:40');
insert into songs(songName,artist_name,genre,album,duration) values('Butter','BTS','Pop','Butter','2:44');
insert into songs(songName,artist_name,genre,album,duration) values('Kesariya','Arjit singh, Amitabh Bhattacharya & Pritam Chakraborty','Indian Pop','Brahmastr','4:28');
insert into songs(songName,artist_name,genre,album,duration) values('I Do not Wanna Live Forever','Zayan Malik, Taylor Swift','Soul,Pop','Fifty Shades of Darker','4:05');
insert into songs(songName,artist_name,genre,album,duration) values('Ek Dil Ek Jaan','Shivam Pathak','Indian Film Pop','Padmavat','3:39');

select*from songs;

create table playlist(
playlistID int primary key auto_increment,
playlistName varchar(15) unique not null);

alter table playlist auto_increment=10;
desc playlist;
select*from playlist;

create table playlistContent(
playlistID int ,
songID int,
foreign key (playlistID) references playlist(playlistID),
foreign key (songID) references songs(songID),
primary key (playlistID, songID));

desc playlistContent;
select*from playlistContent;

insert into playlist(playlistName) values('Sad Song');
insert into playlist(playlistName) values('Pop');
insert into playlist(playlistName) values('english Songs');

insert into playlistContent values(11,2);
insert into playlistContent values(10,4);
insert into playlistContent values(12,1);
insert into playlistContent values(12,2);