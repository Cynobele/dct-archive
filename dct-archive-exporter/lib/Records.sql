SET SQL_MODE = `NO_AUTO_VALUE_ON_ZERO`;
SET time_zone = `+00:00`;


CREATE TABLE IF NOT EXISTS `Records` (
    `PhotoID` int(6) NOT NULL,
    `Publication` varchar(4) NOT NULL,
    `Date` varchar(100) NOT NULL,
    `Reference` int(7) NOT NULL,
    `Location` varchar(15) NOT NULL,
    `Caption` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `Records` (`PhotoID`, `Publication`, `Date`, `Reference`, `Location`, `Caption`) VALUES
