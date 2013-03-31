package cr5.items;

public class Text {

	long	createdAt;
	long	modifiedAt;
	String	text;
	String	url;
	long	genreId;
	long	subGenreId;
	long	dbId;
	long	langId;
	String	memo;
	
	public Text() {
		
	}


	
	public Text(Builder builder) {
		// TODO Auto-generated constructor stub
		createdAt = builder.createdAt;
		modifiedAt = builder.modifiedAt;
		text = builder.text;
		url = builder.url;
		genreId = builder.genreId;
		subGenreId = builder.subGenreId;
		dbId = builder.dbId;
		langId = builder.langId;
		memo = builder.memo;

	}//public Text(Builder builder)

	public static class Builder {

		private long	createdAt;
		private long	modifiedAt;
		private String	text;
		private String	url;
		private long	genreId;
		private long	subGenreId;
		private long	dbId;
		private long	langId;
		private String	memo;

		public Text build() {
			return new Text(this);
		}

		public Builder setMemo(String val) {
			
			memo = val;	return this;
			
		}

		public Builder langId(long val) {
			
			langId = val;	return this;
			
		}

		public Builder setDbId(long val) {
			
			dbId = val;	return this;
			
		}

		public Builder setSubGenreId(long val) {
			
			subGenreId = val;	return this;
			
		}

		public Builder setGenreId(long val) {
			
			genreId = val;	return this;
			
		}

		public Builder setUrl(String val) {
			
			url = val;	return this;
			
		}

		public Builder setText(String val) {
			
			text = val;	return this;
			
		}

		public Builder setModifiedAt(long val) {
			
			modifiedAt = val;	return this;
			
		}

		public Builder setCreatedAt(long val) {
			
			createdAt = val;	return this;
			
		}

	}//public static class Builder
	
	
	
	public long getCreatedAt() {
		return createdAt;
	}
	public long getModifiedAt() {
		return modifiedAt;
	}
	public String getText() {
		return text;
	}
	public String getUrl() {
		return url;
	}
	public long getGenreId() {
		return genreId;
	}
	public long getSubGenreId() {
		return subGenreId;
	}
	public long getDbId() {
		return dbId;
	}
	public long getLangId() {
		return langId;
	}
	public String getMemo() {
		return memo;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public void setModifiedAt(long modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
	public void setSubGenreId(long subGenreId) {
		this.subGenreId = subGenreId;
	}
	public void setDbId(long dbId) {
		this.dbId = dbId;
	}
	public void setLangId(long langId) {
		this.langId = langId;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
}
