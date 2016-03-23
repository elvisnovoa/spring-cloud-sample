drop table oauth_refresh_token if exists;
drop table oauth_access_token if exists;
drop table authorities if exists;
drop table users if exists;
drop table oauth_client_details if exists;

	create table oauth_client_details (
	  client_id VARCHAR(256) PRIMARY KEY,
	  resource_ids VARCHAR(256),
	  client_secret VARCHAR(256),
	  scope VARCHAR(256),
	  authorized_grant_types VARCHAR(256),
	  web_server_redirect_uri VARCHAR(256),
	  authorities VARCHAR(256),
	  access_token_validity INTEGER,
	  refresh_token_validity INTEGER,
	  additional_information VARCHAR(4096),
	  autoapprove VARCHAR(256)
	);
	
  create table users(
	  username varchar_ignorecase(50) not null primary key,
	  password varchar_ignorecase(256) not null,
	  enabled boolean not null);
	  
  create table authorities (
	  username varchar_ignorecase(50) not null,
	  authority varchar_ignorecase(50) not null,
	  constraint fk_authorities_users foreign key(username) references users(username));
	  create unique index ix_auth_username on authorities (username,authority);
	  
	create table oauth_access_token (
	  token_id VARCHAR(256),
	  token LONGVARBINARY,
	  authentication_id VARCHAR(256),
	  user_name VARCHAR(256),
	  client_id VARCHAR(256),
	  authentication LONGVARBINARY,
	  refresh_token VARCHAR(256)
	);
	
	create table oauth_refresh_token (
	  token_id VARCHAR(256),
	  token LONGVARBINARY,
	  authentication LONGVARBINARY
	);