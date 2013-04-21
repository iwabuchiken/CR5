package cr5.items;

import cr5.items.Text.Builder;

public class Word {

	long	db_id;
	long	createdAt;
	long	modifiedAt;
	
	String w1;
	String w2;
	String w3;
	
	String text_ids;
	long text_id;
	long lang_id;
	
	long	remoteDbId;
	long	createdAt_mill;
	long	updatedAt_mill;
	
	public Word(Builder builder) {
		
		db_id = builder.db_id;
		createdAt = builder.createdAt;
		modifiedAt = builder.modifiedAt;
		
		w1 = builder.w1;
		w2 = builder.w2;
		w3 = builder.w3;
		
		text_ids = builder.text_ids;
		text_id = builder.text_id;
		lang_id = builder.lang_id;
		
		remoteDbId = builder.remoteDbId;
		createdAt_mill = builder.createdAt_mill;
		updatedAt_mill = builder.updatedAt_mill;

		
	}//public Text(Builder builder)

	public long getDb_id() {
		return db_id;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public long getModifiedAt() {
		return modifiedAt;
	}
	public String getW1() {
		return w1;
	}
	public String getW2() {
		return w2;
	}
	public String getW3() {
		return w3;
	}
	public String getText_ids() {
		return text_ids;
	}
	public long getText_id() {
		return text_id;
	}
	public long getLang_id() {
		return lang_id;
	}
	public long getRemoteDbId() {
		return remoteDbId;
	}
	public long getCreatedAt_mill() {
		return createdAt_mill;
	}
	public long getUpdatedAt_mill() {
		return updatedAt_mill;
	}

	public static class Builder {

		private long	db_id;
		private long	createdAt;
		private long	modifiedAt;
		
		private String w1;
		private String w2;
		private String w3;
		
		private String text_ids;
		private long text_id;
		private long lang_id;
		
		private long	remoteDbId;
		private long	createdAt_mill;
		private long	updatedAt_mill;

		
		public Word build() {
			return new Word(this);
		}


		public Builder setDb_id(long db_id) {
			this.db_id = db_id;	return this;
		}


		public Builder setCreatedAt(long createdAt) {
			this.createdAt = createdAt;	return this;
		}


		public Builder setModifiedAt(long modifiedAt) {
			this.modifiedAt = modifiedAt;	return this;
		}


		public Builder setW1(String w1) {
			this.w1 = w1;	return this;
		}


		public Builder setW2(String w2) {
			this.w2 = w2;	return this;
		}


		public Builder setW3(String w3) {
			this.w3 = w3;	return this;
		}


		public Builder setText_ids(String text_ids) {
			this.text_ids = text_ids;	return this;
		}


		public Builder setText_id(long text_id) {
			this.text_id = text_id;	return this;
		}


		public Builder setLang_id(long lang_id) {
			this.lang_id = lang_id;	return this;
		}


		public Builder setRemoteDbId(long remoteDbId) {
			this.remoteDbId = remoteDbId;	return this;
		}


		public Builder setCreatedAt_mill(long createdAt_mill) {
			this.createdAt_mill = createdAt_mill;	return this;
		}


		public Builder setUpdatedAt_mill(long updatedAt_mill) {
			this.updatedAt_mill = updatedAt_mill;	return this;
		}

		
	}//public static class Builder
	
}
