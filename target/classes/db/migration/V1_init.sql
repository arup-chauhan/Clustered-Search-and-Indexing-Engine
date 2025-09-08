CREATE TABLE IF NOT EXISTS tag (
                                   id BIGSERIAL PRIMARY KEY,
                                   name VARCHAR(128) UNIQUE NOT NULL
    );


CREATE TABLE IF NOT EXISTS document_meta (
                                             id VARCHAR(64) PRIMARY KEY,
    title VARCHAR(512) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
    );


CREATE TABLE IF NOT EXISTS document_tag (
                                            document_id VARCHAR(64) REFERENCES document_meta(id) ON DELETE CASCADE,
    tag_id BIGINT REFERENCES tag(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, tag_id)
    );