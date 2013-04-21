package cr5.items;

import cr5.items.Word.Builder;

public class WordList {

	long	db_id;
	long	created_at;
	long	updated_at;
	
	long	text_id;
	long	word_id;
	long	lang_id;
	
	long	remote_db_id;
	long	created_at_mill;
	long	updated_at_mill;
	
	public WordList(Builder builder) {

		db_id = builder.db_id;
		created_at = builder.created_at;
		updated_at = builder.updated_at;
		
		text_id = builder.text_id;
		word_id = builder.word_id;
		lang_id = builder.lang_id;
		
		remote_db_id = builder.remote_db_id;
		created_at_mill = builder.created_at_mill;
		updated_at_mill = builder.updated_at_mill;

	}
	
	public long getDb_id() {
		return db_id;
	}
	public long getCreated_at() {
		return created_at;
	}
	public long getUpdated_at() {
		return updated_at;
	}
	public long getText_id() {
		return text_id;
	}
	public long getWord_id() {
		return word_id;
	}
	public long getLang_id() {
		return lang_id;
	}
	public long getRemote_db_id() {
		return remote_db_id;
	}
	public long getCreated_at_mill() {
		return created_at_mill;
	}
	public long getUpdated_at_mill() {
		return updated_at_mill;
	}


	public static class Builder {

		private long	db_id;
		private long	created_at;
		private long	updated_at;
		
		private long	text_id;
		private long	word_id;
		private long	lang_id;
		
		private long	remote_db_id;
		private long	created_at_mill;
		private long	updated_at_mill;

		public WordList build() {
			return new WordList(this);
		}

		public Builder setDb_id(long db_id) {
			this.db_id = db_id;	return this;
		}

		public Builder setCreated_at(long created_at) {
			this.created_at = created_at;	return this;
		}

		public Builder setUpdated_at(long updated_at) {
			this.updated_at = updated_at;	return this;
		}

		public Builder setText_id(long text_id) {
			this.text_id = text_id;	return this;
		}

		public Builder setWord_id(long word_id) {
			this.word_id = word_id;	return this;
		}

		public Builder setLang_id(long lang_id) {
			this.lang_id = lang_id;	return this;
		}

		public Builder setRemote_db_id(long remote_db_id) {
			this.remote_db_id = remote_db_id;	return this;
		}

		public Builder setCreated_at_mill(long created_at_mill) {
			this.created_at_mill = created_at_mill;	return this;
		}

		public Builder setUpdated_at_mill(long updated_at_mill) {
			this.updated_at_mill = updated_at_mill;	return this;
		}

		
		
	}//public static class Builder
}
