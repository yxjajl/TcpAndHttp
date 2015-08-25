package com.guaua;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Book implements Comparable<Book> {
	private String author;
	private String title;
	private String publisher;
	private String isbn;
	private double price;

	@Override
	public int compareTo(Book o) {
		return ComparisonChain.start()
				// 链式比较,在第一个非0处返回
				.compare(this.title, o.getTitle()).compare(this.author, o.getAuthor()).compare(this.publisher, o.getPublisher()).compare(this.isbn, o.getIsbn()).compare(this.price, o.getPrice())
				.result();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(title, author, publisher, isbn);// hashCode生成
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)// toString
				// .omitNullValues() // 忽略null属性
				.add("title", title)
				.add("author", author)
				.add("publisher", publisher)
				.add("price", price)
				.add("isbn", isbn)
				.toString();
	}

	public void tt() {
		String str = null;
		str = Objects.firstNonNull(str, "defaut value");
		System.out.println(str);
	}

	public static void main(String[] args) {
		Book book = new Book();
		System.out.println(book.hashCode());
		System.out.println(book.toString());
		book.tt();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
