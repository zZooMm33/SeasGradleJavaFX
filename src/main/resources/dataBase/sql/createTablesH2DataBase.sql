CREATE TABLE IF NOT EXISTS Oceans (
    Id INTEGER PRIMARY KEY auto_increment NOT NULL,
    Name varchar(255) NOT NULL,
    Description varchar(1000)
);
CREATE TABLE IF NOT EXISTS Seas (
    Id INTEGER PRIMARY KEY auto_increment NOT NULL,
    Name varchar(255) NOT NULL,
    Square INTEGER NOT NULL,
    MaxDepth INTEGER NOT NULL,
    IdOcean INTEGER references Oceans(Id),
    Description varchar(1000)
);