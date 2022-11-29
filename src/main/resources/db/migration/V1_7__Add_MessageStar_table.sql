create sequence message_star_seq start 1 increment 1;

CREATE TABLE message_star
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id    BIGINT                                  NOT NULL,
    message_id BIGINT                                  NOT NULL,
    persist_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_message_star PRIMARY KEY (id)
);

ALTER TABLE message_star
    ADD CONSTRAINT FK_MESSAGE_STAR FOREIGN KEY (message_id) REFERENCES message (id);