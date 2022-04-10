-- SQL file for creating and storing records data

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


-- Table structure for table 'Records'

CREATE TABLE IF NOT EXISTS 'Records' (
    'PhotoID' int(6) NOT NULL,
    'PubID' int(2) NOT NULL,
    'Date' varchar(100) NOT NULL,
    'Reference' int(7) NOT NULL,
    'Location' varchar(15) NOT NULL,
    'Caption' varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--Dumping the data

INSERT INTO 'Records' ('PhotoID', 'PubID', 'Date', 'Reference', 'Location', 'Caption') VALUES
--This is where the program loops over the data and inserts on lines (remember to add a line counter to increment after each write)