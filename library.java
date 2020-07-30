/*This program implements the concept of Inheritance.
Library is an abstract class.
Classes Admin and Student inherit the class library.
They override the viewAvailableBooks() function.
There are exception handlers to handle the RuntimeExceptions such as InputMismatchException and NoSuchElementException.
LocalDate class and ArrayList is also used.
*/
import java.io.*;
import java.util.*;
import java.time.*;
class Book
{
	int book_id,count;
	String book_name,subject,author;
	Book()
	{
		book_id=0;
		count=0;
		book_name="";
		subject="";
		author="";
	}
	Book(int id,String name,String sub,String auth,int c)
	{
		book_id=id;
		count=c;
		book_name=name;
		subject=sub;
		author=auth;
	}
	public String toString()
	{
		return book_id+"\t"+book_name+"\t"+subject+"\t"+author+"\t"+count;
	}
}
abstract class Library // Abstract class used
{
	static ArrayList <Book> m= new ArrayList<>();
	static ArrayList <Student> students= new ArrayList<>();
	String names[]={"Riya","Ashok","Priya","Shreya","Siddharth","Sahil","Yash","Riddhi","Anjali","Pankaj"};
	public void initialize()
	{
	m.add(new Book(101,"Higher Engineering Mathematics","maths","B. S. Garewal",10));
	m.add(new Book(102,"Advanced Engineering Mathematics","maths","Erwin Kreyszig",12));
	m.add(new Book(103,"Mathematics for Engineers","maths","R. K. Jain",15));
	m.add(new Book(104,"Advanced Probability Theory","maths","Janos Galambos",16));
	m.add(new Book(105,"Discrete Mathematics and its Applications","maths","Kenneth H. Rosen",14));
	
	m.add(new Book(201,"Higher Engineering Physics","physics","H. K. Malik",10));
	m.add(new Book(202,"Engineering Physics","physics","D. K. Bhattacharya",12));
	m.add(new Book(203,"Modern Engineering Physics","physics","A S Vasudeva",15));
	m.add(new Book(204,"Concepts of Physics","physics","H. C. Verma",13));
	m.add(new Book(205,"Principles Of Physics","physics","P. V. Naik",18));
	
	m.add(new Book(301,"Fundamentals of Software Engineering","computer science","Rajib Mall",10));
	m.add(new Book(302,"Let Us C","computer science","Ashutosh Pandey",12));
	m.add(new Book(303,"Computer Networks","computer science","Andrew S. Tanenbaum",15));
	m.add(new Book(304,"Data structures and algorithms in C++","computer science","Adam Drozdek",13));
	m.add(new Book(305,"Java The Complete Reference","computer science","Herbert Schildt",18));
	
	m.add(new Book(401,"Cambridge English for Engineering","english","Mark Ibbotson",10));
	m.add(new Book(402,"Professional English in Use","english","Mark Ibbotson",12));
	m.add(new Book(403,"English Conversation Practice","english","Grant Taylor",15));
	m.add(new Book(404,"Wren & Martin","english","P. C. Wren",16));
	m.add(new Book(405,"Oxford Modern English Grammar","english","Bas Aarts",14));
	
	for(int i=0;i<10;i++)
	{
		students.add(new Student(11800+(i+1),names[i]));
	}
	}
	public Book get_details()
	{
		int c=0;
		Scanner sc= new Scanner(System.in);
		System.out.println("\nEnter Book id:");
		int book_id=sc.nextInt();
		sc.nextLine();
		for (Book b:m)
			{
				if(b.book_id==book_id)
					c=1;
			}
		if(c==1)
		{
			throw new RuntimeException("this book id already exists!");
		}
		System.out.println("\nEnter Book Name:");
		String book_name=sc.nextLine();
		System.out.println("\nEnter subject:");
		String subject=sc.nextLine();
		System.out.println("\nEnter author Name:");
		String author=sc.nextLine();
		System.out.println("\nEnter the number of copies:");
		int count=sc.nextInt();
		Book bk=new Book(book_id,book_name,subject,author,count);
		return bk;
	}	
	public abstract void viewAvailableBooks();//abstract function
}
class Admin extends Library
{
	public void addBooks()
	{
		try
		{
		int f=0;
		Book bk=get_details();
		super.m.add(bk);
		System.out.println("\nBook Successfully added....!!!\n");
		}
		catch(InputMismatchException ex)
		{
			System.out.println("\nIncorrect input!");
		}
		catch(RuntimeException t)
		{
			System.out.println(t);
		}
	}
	public void viewAvailableBooks()// overriding the abstract function
	{
		try
		{
		Scanner sc= new Scanner(System.in);
		System.out.println("\nEnter your choice:\n1-view subject wise\n2-view all");
		int ch=sc.nextInt();
		sc.nextLine();
		if(ch==1)
		{
			System.out.println("\nEnter subject:");
			String s=sc.nextLine();
			s=s.trim();
			int f=0;
			System.out.println("book id\tbook name\tauthor\tcopies");
			for (Book b:super.m)
			{
				if(b.subject.equalsIgnoreCase(s))
				{
					System.out.println(b.book_id+"\t"+b.book_name+"\t"+b.author+"\t"+b.count);
					f=1;
				}
			}
			if(f==0)
			{
				throw new NoSuchElementException("Subject not found...!!!");
			}
		}
		else if(ch==2)
		{
			for (Book b:super.m)
			{
				System.out.println(b);
			}
		}
		else 
		{
			System.out.println("\nInvalid input!");
		}
		}
		catch(InputMismatchException ex)
		{
			System.out.println("\nIncorrect input!");
		}
		catch(NoSuchElementException u)
		{
			System.out.println(u);
		}
	}
	public void deleteBook()
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("\nEnter subject for which you want to delete the record:");
		String s=sc.nextLine();
		System.out.println();
		for (Book b:super.m)
			{
				if(b.subject.equalsIgnoreCase(s))
				System.out.println(b);
			}
		try
		{
		System.out.println("\nEnter the record id which you want to delete:");
		int rec = sc.nextInt();
		int i=0;
		for (Book b:super.m)
			{
				if(b.book_id==rec)
					i=m.indexOf(b);
				else
					i=-1;
			}
		if(i!=-1)
		{
			m.remove(i);
			System.out.println("\nRecord successfully deleted...!!!");
		}
		else
		{
			throw new NoSuchElementException("No such record found...!!!");
		}
		}
		catch(InputMismatchException ex)
		{
			System.out.println("\nIncorrect input!");
		}
		catch(NoSuchElementException u)
		{
			System.out.println(u);
		}
	}
}

class Student extends Library
{
	String name,i_book;
	int reg_no,flag=0;
	LocalDate i_date,r_date;
	Scanner sc= new Scanner(System.in);
	Student()
	{
		name="";
		reg_no=0;
	}
	Student(int reg,String n)
	{
		name=n;
		reg_no=reg;
	}
	public void viewAvailableBooks()// Overriding the abstract function
	{
		try
		{
		System.out.println("\nEnter your choice:\n1-Search by subject\n2-Search by book name");
		int ch=sc.nextInt();
		sc.nextLine();
		if(ch==1)
		{
			System.out.println("\nEnter subject:");
			String s=sc.nextLine();
			s=s.trim();
			int f=0;
			System.out.println("book id\t\tbook name\t\tauthor\t\tcopies");
			for (Book b:super.m)
			{
				if(b.subject.equalsIgnoreCase(s))
				{
					System.out.println("\n"+b.book_id+"\t"+b.book_name+"\t"+b.author+"\t"+b.count);
					f=1;
				}
			}
			if(f==0)
			{
				throw new NoSuchElementException("Subject not found...!!!");
			}
		}
		else if(ch==2)
		{
			System.out.println("\nEnter book name:");
			String s=sc.nextLine();
			s=s.trim();
			int f=0;
			for (Book b:super.m)
			{
				if(b.book_name.equalsIgnoreCase(s))
				{
					System.out.println("\n"+b.book_id+"\t"+b.book_name+"\t"+b.author+"\t"+b.count);
					f=1;
				}
			}
			if(f==0)
			{
				throw new NoSuchElementException("Book not found...!!!");
			}
		}
		}
		catch(InputMismatchException ex)
		{
			System.out.println("\nIncorrect input!");
		}
		catch(NoSuchElementException u)
		{
			System.out.println(u);
		}
	}
	public void issue_book()
	{
		if(flag==1)
		{
			System.out.println("\nYou have already issued a book! Please return the book if you want to issue another book!");
		}
		else
		{
			try
			{
			int f=0;
			System.out.println("Enter Book id:");
			int id=sc.nextInt();
			Book bk=new Book();
			for(Book b:super.m)
			{
				if(b.book_id==id)
				{
					bk=b;
					f=1;
				}
			}
			if(f==0)
			{
				throw new NoSuchElementException("Invalid book id...!!!");
			}
			else
			{
				if(bk.count==0)
					System.out.println("\nThis book is out of stock!");
				else
				{
					i_book=bk.book_name;
					bk.count-=1;
					this.flag=1;
					i_date=LocalDate.now();
					r_date=i_date.plusWeeks(1);
					System.out.println("\nSuccessfully issued!"+
					"\nissue date: "+i_date+"\treturn date: "+r_date+
					"\nPlease collect your book!");
				}
					
			}
			}
			catch(InputMismatchException ex)
			{
				System.out.println("\nIncorrect input!");
			}
			catch(NoSuchElementException u)
			{
				System.out.println(u);
			}
		}
	}
	public void return_book()
	{
		if(flag==1)
		{
		Book bk=new Book();
		for (Book b:super.m)
			{
				if(b.book_name.equalsIgnoreCase(i_book))
				{
					bk=b;
				}
			}
		bk.count+=1;
		System.out.println("\nSuccessfully returned the book "+i_book+"! \nPlease keep the book at the counter!");
		this.flag=0;
		}
		else
		{
			System.out.println("\nYou have not issued any book!");
		}
	}
}

class execute
{
	public static void main(String args[])
	{
		
		
		int ch=0;
		
		do
		{
			try
			{
				Scanner sc= new Scanner(System.in);
				System.out.println("\n1-Admin login\n2-user login\n3-exit");
				ch=sc.nextInt();
				sc.nextLine();
				
				if(ch==1)
				{
					System.out.println("\nEnter User Name:");
					String u_name=sc.nextLine();
					if(u_name.equals("admin"))
					{
						System.out.println("\nEnter Password:");
						String password=sc.nextLine();
						if (password.equals("admin@123"))
						{
							System.out.println("\nlogin successful....!!!");
							System.out.println("\nHii Admin! What operation would you like to perform?");
							int choice;
							Admin obj=new Admin();
							obj.initialize();
							do
							{
							System.out.println("\nEnter:\n1- add books\n2-view books\n3-remove books\n4-log out");
							choice=sc.nextInt();
							switch(choice)
							{
								case 1:obj.addBooks();
										System.out.println();
										break;
								case 2:obj.viewAvailableBooks();
										break;
								case 3:	obj.deleteBook();
										break;
								case 4: break;
								default:System.out.println("Invalid choice!");
							}
							}while(choice==1 || choice==2 || choice==3);
						}
						else
						{
							System.out.println("\nIncorrect password!");
						}
					}
					else
					{
						System.out.println("\nInvalid username!");
					}
				}
				else if (ch==2)
				{
					System.out.println("\nHello Student ! Enter your name:");
					String nam=sc.nextLine();
					System.out.println("\nEnter your registration number:");
					int f=0;
					Student obj=new Student();
					obj.initialize();
					int num=sc.nextInt();
					for(Student s:Library.students)
					{
						if(s.name.equalsIgnoreCase(nam))
						{
							f=1;
							if(s.reg_no==num)
							{
								f=2;
								obj=s;
							}
						}
					}
					if(f==0)
						System.out.println("\nInvalid name!");
					else if(f==1)
						System.out.println("\nInvalid registration number!");
					else
					{
						System.out.println("\nlogin successful....!!!");
						int choice;
						do
						{
						System.out.println("\nEnter:\n1-search\n2-issue book\n3-return book\n4-log out");
						choice=sc.nextInt();
						switch(choice)
						{
							case 1:obj.viewAvailableBooks();
									break;
							case 2: obj.issue_book();
									//System.out.println("Student flag: "+obj.flag);
									break;
							case 3: obj.return_book();
									//System.out.println("Student flag: "+obj.flag);
									break;
							case 4: break;
							default:System.out.println("Invalid choice!");
						}
						}while(choice==1||choice==2||choice==3);
					}
				}
			}
			catch(RuntimeException e)
			{
				ch=1;
				System.out.println("\nIncorrect input!");
			}
		}while(ch==1|| ch==2);
		
	}
}
