public class RecursivFib
{
  public long fib(int i)
  {
    return (i==0||i==1)?1:fib(i-1)+fib(i-2);
  }
}
