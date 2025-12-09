import java.util.Scanner;

public class MusicPlayer{

  // String music[] = new music[];

  static void musicList(String music){
    System.out.println("Daftar music");
  }
  
  static void musicJudul(String judul){
    
  }

  public static void main(String [] args){
      Scanner in = new Scanner(System.in);

      int pilihan = 0;

      do{
        System.out.println("ONLINE MUSIC PLAYER");
        System.out.println("1. Lihat semua musik");
        System.out.println("2. Cari musik");
        System.out.println("3. Tambah musik");
        pilihan = in.nextInt();
        break;
      }
      while (pilihan != 0); {
        System.out.println("Keluar dari aplikasi");
      }

      
    
      


  }
}