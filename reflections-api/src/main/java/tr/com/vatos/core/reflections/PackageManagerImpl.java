package tr.com.vatos.core.reflections;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import sun.net.www.protocol.file.FileURLConnection;
import tr.com.vatos.core.common.utils.NullCheckUtils;


class PackageManagerImpl implements PackageManager
{
	
	static PackageManagerImpl instance = new PackageManagerImpl();
	
	private PackageManagerImpl() {}

	// class Methods
	
	@Override
	public Collection<Class<?>> getAllClasses(String... packages) {
		return scanPackages(SourceAcceptanceAction.IS_CLASS_BEHAVIOUR, packages) ;
	}
	
	@Override
	public Collection<Class<?>> getAllClassesWithAnnotation(Class<? extends Annotation> annotation,String... packages) {
		return getAllClasses(packages)
				.stream()
				.filter(cls -> NullCheckUtils.isNotObjectEmpty(cls.getAnnotation(annotation)))
				.collect(Collectors.toSet());
	}

	@Override
	public <T> Collection<Class<? extends T>> getAllClassesExtendsClass(Class<T> subType, String... packages) {
		return getAllClasses(packages)
				.stream()
				.filter(cls -> cls.isAssignableFrom(subType))
				.map(cls -> (Class<T>)cls)
				.collect(Collectors.toSet());
	}

	@Override
	public <T> Collection<Class<? extends T>> getAllClassesImplementsInterface(Class<T> subType, String... packages) {
		return getAllClasses(packages)
				.stream()
				.filter(cls -> cls.isAssignableFrom(subType))
				.map(cls -> (Class<T>)cls)
				.collect(Collectors.toSet());
	}
	
	// Interface Methods
	
	@Override
	public Collection<Class<?>> getAllInterfaces(String... packages) {
		return scanPackages(SourceAcceptanceAction.IS_INTERFACE_BEHAVIOUR, packages);
	}

	// Enum Methods
	
	@Override
	public Collection<Class<?>> getAllEnums(String... packages) {
		return scanPackages(SourceAcceptanceAction.IS_ENUM_BEHAVIOUR, packages);
	}

	// Annotation Methods
	
	@Override
	public Collection<Class<?>> getAllAnnotations(String... packages) {
		return scanPackages(SourceAcceptanceAction.IS_ANNOTATION_BEHAVIOUR, packages);
	}
	
	// private methods
	
	private Collection<Class<?>> scanPackages(SourceAcceptanceAction action,String... packages)
	{
		final Map<String,Class<?>> classMap = new HashMap<String, Class<?>>();
		if(NullCheckUtils.isNotArrayEmpty(packages))
		{
			for(String packagePrefix : packages)
			{
				try 
				{
					scanPackage(packagePrefix,classMap,action);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		return classMap.values();
	}
	
	
	private void scanPackage(String pckgname,Map<String,Class<?>> classMap,SourceAcceptanceAction action) throws ClassNotFoundException {
	    try {
	        final ClassLoader cld = Thread.currentThread().getContextClassLoader();
	        if (cld == null)
	            throw new ClassNotFoundException("Can't get class loader.");
	        final Enumeration<URL> resources = cld.getResources(pckgname.replace('.', '/'));
	        URLConnection connection;
	        for (URL url = null; resources.hasMoreElements() && ((url = resources.nextElement()) != null);) 
	        {
	            try 
	            {
	                connection = url.openConnection();
	                if (connection instanceof JarURLConnection) 
	                {
	                    checkJarFile((JarURLConnection) connection, pckgname,classMap,action);
	                } 
	                else if (connection instanceof FileURLConnection) 
	                {
	                    try 
	                    {
	                        checkDirectory(new File(URLDecoder.decode(url.getPath(), "UTF-8")), pckgname, classMap,action);
	                    } 
	                    catch (final UnsupportedEncodingException ex) 
	                    {
	                        throw new ClassNotFoundException(pckgname+ " does not appear to be a valid package (Unsupported encoding)",ex);
	                    }
	                } 
	                else
	                    throw new ClassNotFoundException(pckgname + " ("+ url.getPath()+ ") does not appear to be a valid package");
	            } 
	            catch (final IOException ioex) 
	            {
	                throw new ClassNotFoundException("IOException was thrown when trying to get all resources for "+ pckgname, ioex);
	            }
	        }
	    } 
	    catch (final NullPointerException ex) 
	    {
	        throw new ClassNotFoundException(pckgname+ " does not appear to be a valid package (Null pointer exception)",ex);
	    } 
	    catch (final IOException ioex) 
	    {
	        throw new ClassNotFoundException("IOException was thrown when trying to get all resources for "+ pckgname, ioex);
	    }
	}
	
	
	private void checkDirectory(File directory, String pckgname,Map<String,Class<?>> classMap, SourceAcceptanceAction action) throws ClassNotFoundException {
	    File tmpDirectory;
	    if (directory.exists() && directory.isDirectory()) 
	    {
	        final String[] files = directory.list();
	        for (final String file : files) 
	        {
	            if (file.endsWith(".class"))
	            {
	                try 
	                {
	                	Class<?> clazz = Class.forName(pckgname + '.' + file.substring(0, file.length() - 6));
	                	if(action.isAcceptable(clazz)) {
	                		classMap.put(clazz.getName(), clazz);
	                	}
	                } 
	                catch (final NoClassDefFoundError e) 
	                {
	                	e.printStackTrace();
	                }
	            } 
	            else if ((tmpDirectory = new File(directory, file)).isDirectory()) 
	            {
	                checkDirectory(tmpDirectory, pckgname + "." + file, classMap,action);
	            }
	        }
	    }
	}

	
	private void checkJarFile(JarURLConnection connection, String pckgname, Map<String,Class<?>> classMap, SourceAcceptanceAction action) throws ClassNotFoundException, IOException 
	{
		StringBuilder sb = new StringBuilder();
	    final JarFile jarFile = connection.getJarFile();
	    final Enumeration<JarEntry> entries = jarFile.entries();
	    String name;
	    for (JarEntry jarEntry = null; entries.hasMoreElements() && ((jarEntry = entries.nextElement()) != null);)
	    {
	        name = jarEntry.getName();
	        if (name.contains(".class")) 
	        {
	            name = name.substring(0, name.length() - 6).replace('/', '.');
	            if (name.contains(pckgname+".")) 
	            {
	            	try 
	            	{
	            		Class<?> clazz = Class.forName(name);
	            		if(action.isAcceptable(clazz)) {
	            			classMap.put(clazz.getName(), clazz);
	            		}
	            	}
	            	catch(NoClassDefFoundError e) 
	            	{
	            		e.printStackTrace();
	            	}
	                
	            }
	        }
	    }
	    Files.write(Paths.get("log.txt"), sb.toString().getBytes());
	}

	

	
	

}
