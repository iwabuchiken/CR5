package cr5.items;

public class Word {

	String	w1;
	String	w2;
	String	w3;
	
	long	createdAt;
	long	modifiedAt;
	long	createdAt_mill;

	long	textId;
	String	textIds;
	long	langId;
	
	long	remoteDBId;
	long	localDBId;
	
	public Word() {
		
	}

	

	
	
	public Word(Builder builder) {
		// TODO Auto-generated constructor stub
		w1 = builder.w1;
		w2 = builder.w2;
		w3 = builder.w3;
		
		createdAt = builder.createdAt;
		modifiedAt = builder.modifiedAt;
		createdAt_mill = builder.createdAt_mill;

		textId = builder.textId;
		textIds = builder.textIds;
		langId = builder.langId;
		
		remoteDBId = builder.remoteDBId;
		localDBId = builder.localDBId;

		
	}//public Text(Builder builder)

	public static class Builder {

		private String	w1;
		private String	w2;
		private String	w3;
		
		private long	createdAt;
		private long	modifiedAt;
		private long	createdAt_mill;

		private long	textId;
		private String	textIds;
		private long	langId;
		
		private long	remoteDBId;
		private long	localDBId;


		public Word build() {
			return new Word(this);
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


		public Builder setCreatedAt(long createdAt) {
			this.createdAt = createdAt;	return this;
		}


		public Builder setModifiedAt(long modifiedAt) {
			this.modifiedAt = modifiedAt;	return this;
		}


		public Builder setCreatedAt_mill(long createdAt_mill) {
			this.createdAt_mill = createdAt_mill;	return this;
		}


		public Builder setTextId(long textId) {
			this.textId = textId;	return this;
		}


		public Builder setTextIds(String textIds) {
			this.textIds = textIds;	return this;
		}


		public Builder setLangId(long langId) {
			this.langId = langId;	return this;
		}


		public Builder setRemoteDBId(long remoteDBId) {
			this.remoteDBId = remoteDBId;	return this;
		}


		public Builder setLocalDBId(long localDBId) {
			this.localDBId = localDBId;	return this;
		}

		
	}//public static class Builder


	public String getW1() {
		return w1;
	}





	public String getW2() {
		return w2;
	}





	public String getW3() {
		return w3;
	}





	public long getCreatedAt() {
		return createdAt;
	}





	public long getModifiedAt() {
		return modifiedAt;
	}





	public long getCreatedAt_mill() {
		return createdAt_mill;
	}





	public long getTextId() {
		return textId;
	}





	public String getTextIds() {
		return textIds;
	}





	public long getLangId() {
		return langId;
	}





	public long getRemoteDBId() {
		return remoteDBId;
	}





	public long getLocalDBId() {
		return localDBId;
	}
	
	
	
}
