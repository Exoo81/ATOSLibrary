# ATOSLibrary

@Author: Marcin Piskor

<h1>Library Maven Spring application</h1>

<h3>Running ATOSLibrary locally</h3>
<pre>
<h4>1. Clone the repository from GitHub:</h4>
    <ul><li> $ git clone https://github.com/Exoo81/ATOSLibrary.git</li></ul>
<h4>2. Navigate into the cloned repository directory:</h4>
    <ul><li> $ cd ATOSLibrary</li></ul>
<h4>3. The project uses Maven to build:</h4>
    <ul><li> $ mvn clean install</li></ul>
<h4>4. Running ATOSLibrary locally (tested on Tomcat8):</h4>
    <ul><li> mvn tomcat7:run</li></ul>
 </pre>
<pre>You can then access ATOSLibrery locally here: http://localhost:8080/ATOSLibrary/</pre>
 
<h3>Tech. stack used</h3>
<pre>
<ul>
<li> Java8</li>
<li> Maven</li>
<li> Spring</li>
<li> Tomcat8</li>
<li> jUnit</li>
<li> Hibernate</li>
<li> HSQLDB</li>
<li> Eclipse</li>
</ul>
</pre>

<h3>To generate valid data in HSQLDB</h3>

Go to package <b>com.atos.library.service</b> in <b>InitDBService.class</b> and <b><i><u>uncomment</u></i></b> createData() method (in @PostConstruct void initDB() method)
<div class="highlight highlight-text-html-php">
<pre>
@PostConstruct
public void initDB(){
	createData();				//uncomment to initiate some data in DB
}
</pre>
</div>

In Eclipse Console you can read results of implemented methods based on genetated data. 

<h3>To run some of implemented jUnit tests</h3>

Go to package <b>com.atos.library.service</b> in <b>InitDBService.class</b> and <b><i><u>comment</u></i></b> createData() method (in @PostConstruct void initDB() method)

<div class="highlight highlight-text-html-php">
<pre>
@PostConstruct
public void initDB(){
	//createData();				//uncomment to initiate some data in DB
}
</pre>
</div>



