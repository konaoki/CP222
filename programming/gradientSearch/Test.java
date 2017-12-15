public class Test
{
	static GradientSearcher searcher;
	public static void main(String[] args){
		searcher = new GradientSearcher();
		searcher.generateGradient();
		visualTest();
		test();


		
	}
	public static void test()
	{
		long s;
		long e;
		s = System.currentTimeMillis();
		int num = 200000;
		for(int i=0; i<num; i++)
		{
			searcher.searchWithIndex(130);
			searcher.searchWithIndex(510);
			searcher.searchWithIndex(390);
			searcher.searchWithIndex(380);
			searcher.searchWithIndex(78);
		}


		e = System.currentTimeMillis();
		System.out.println("searched from 1000 sources in approximately "+((e-s)*1000)/(num*5)+"ms");
		System.out.println("searched from "+num*5+" sources in approximately "+(e-s)+"ms");
	}
	public static void visualTest()
	{
		searcher.printSearchedGrid(130);
		searcher.printSearchedGrid(510);
		searcher.printSearchedGrid(390);
		searcher.printSearchedGrid(380);
		searcher.printSearchedGrid(78);
	}
}