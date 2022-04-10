SET SQL_MODE = `NO_AUTO_VALUE_ON_ZERO`;
SET time_zone = `+00:00`;

CREATE TABLE IF NOT EXISTS `Publications` (
    `PubID` int(2) NOT NULL,
    `Shortcode` varchar(3) NOT NULL,
    `Name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Publications` (`PubID`, `Shortcode`, `Name`) VALUES
