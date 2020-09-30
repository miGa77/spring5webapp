package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Author eric = new Author("Eric", "Evans");
		Book book1 = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(book1);
		book1.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(book1);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "1234512345");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		
		Publisher pub1 = new Publisher("Springer", "Adress");
		
		publisherRepository.save(pub1);
		book1.setPublisher(pub1);
		noEJB.setPublisher(pub1);
		pub1.getBooks().add(book1);
		pub1.getBooks().add(noEJB);
		bookRepository.save(book1);
		bookRepository.save(noEJB);
		publisherRepository.save(pub1);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of books: " + bookRepository.count());
		System.out.println("Number of publisher: " + publisherRepository.count());
	}

}
