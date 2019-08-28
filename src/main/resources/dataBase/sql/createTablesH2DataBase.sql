CREATE TABLE IF NOT EXISTS Oceans (
    Id INTEGER PRIMARY KEY auto_increment NOT NULL,
    Name varchar(255) NOT NULL,
    Description varchar(1000)
);

CREATE TABLE IF NOT EXISTS Seas (
    Id INTEGER PRIMARY KEY auto_increment NOT NULL,
    Name varchar(255) NOT NULL,
    Square INTEGER NOT NULL,
    MaximumDepth INTEGER NOT NULL,
    Ocean INTEGER references Oceans(Id),
    Description varchar(1000)
);


