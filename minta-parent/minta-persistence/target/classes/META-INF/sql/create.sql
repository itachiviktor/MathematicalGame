CREATE SEQUENCE TABLE_ID;
CREATE SEQUENCE USER_ID;
CREATE SEQUENCE FIELD_ID;
CREATE SEQUENCE NUMBER_ID;
CREATE TABLE MTABLE (tableID BIGINT, turn INT, PRIMARY KEY(tableID));
CREATE TABLE MUSER (userID BIGINT, username VARCHAR2(50), password VARCHAR2(50), health INT, wins INT, loses INT, paired INT, mtablefk BIGINT, PRIMARY KEY(userID), FOREIGN KEY(mtablefk) references MTABLE(tableID));
CREATE TABLE FIELD( fieldID BIGINT, columnindex INT, rowindex INT, mtablefk BIGINT, PRIMARY KEY(fieldID), FOREIGN KEY(mtablefk) references MTABLE(tableID));
CREATE TABLE NUMBER( columnindex INT, lineindex INT, nvalue INT, lifecycle VARCHAR2(50), muserfk BIGINT, fieldfk BIGINT, PRIMARY KEY(columnindex, lineindex, muserfk), FOREIGN KEY(fieldfk) references FIELD(fieldID), FOREIGN KEY(muserfk) references MUSER(userID));