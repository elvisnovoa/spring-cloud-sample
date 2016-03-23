--password = novoa
insert into oauth_client_details values ('elvis',null,'$2a$10$AIe0E5qyk.tkELozv0cYFu/UaA2Z4LpelYoDUIn/tZ3lqJnGUsFXa','read,write,delete','password,authorization_code,refresh_token,implicit,client_credentials',null,'ROLE_CLIENT, ROLE_TRUSTED_CLIENT',3600,86400,'{}','trust,read,write,delete') ;

--password = password
insert into users values ('elvis','$2a$10$1UPnmozD6i.Li71rQwwUI.u2VGaRtNT3OOGdjSeeLjjXHXzVacRgC',1) ;

insert into authorities values ('elvis','ROLE_USER') ;
insert into authorities values ('elvis','ROLE_CLIENT') ;
insert into authorities values ('elvis','ROLE_ADMIN') ;
insert into authorities values ('elvis','ROLE_TRUSTED_CLIENT') ;