INSERT INTO MTABLE(tableID, turn) VALUES (-001, 0);
INSERT INTO MUSER(userID, username, password, health , wins, loses, paired, mtablefk) VALUES (-001, 'fremen', 'alma', 10, 100, 0, 0, -001);
INSERT INTO MUSER(userID, username, password, health , wins, loses, paired, mtablefk) VALUES (-002, 'bela', '1234', 10, 100, 0, 0, -001);
INSERT INTO FIELD(fieldID, columnindex, rowindex, mtablefk) VALUES (-001, 0, 0, -001);
INSERT INTO FIELD(fieldID, columnindex, rowindex, mtablefk) VALUES (-002, 10, 0, -001);
INSERT INTO FIELD(fieldID, columnindex, rowindex, mtablefk) VALUES (-003, 0, 10, -001);