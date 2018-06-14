CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  created_at TIMESTAMP,
  PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS token (
  id INT NOT NULL AUTO_INCREMENT,
  data VARCHAR(100),
  created_at TIMESTAMP,
  PRIMARY KEY (id));
  
CREATE TABLE IF NOT EXISTS login_data (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  action_performed VARCHAR(100) NOT NULL,
  created_at TIMESTAMP ,
  PRIMARY KEY (id));
  
  