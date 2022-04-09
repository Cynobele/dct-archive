-- SQL file for creating and storing records data

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


-- Table structure for table 'Records'

CREATE TABLE IF NOT EXISTS 'Publications' (
    'PubID' int(2) NOT NULL,
    'Shortcode' varchar(3) NOT NULL,
    'Name' varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--Dumping the data

INSERT INTO 'Publications' ('PubID', 'Shortcode', 'Name') VALUES
--This is where the program loops over the data and inserts on lines (remember to add a line counter to increment after each write)



--Adding Primary Key
ALTER TABLE 'Publications'
    ADD PRIMARY KEY ('PubID');