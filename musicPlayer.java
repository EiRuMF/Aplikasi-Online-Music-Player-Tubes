import java.util.Scanner;

public class musicPlayer{

   String music[] = new String [1000];

  static void tambahMusic(String judul,String artis,String durasi,String album){

  }

  static void tambahPlaylist(String music){

  }

  static void cariData(String tambahMusic){

  }

  static void editDataPlaylist(String tambahPlaylist){

  }

  static void hapusDataPlaylist(String tambahPlaylist){

  }

  static void editDataMusic(String tambahMusic){

  }

  static void hapusDataMusic(String tambahMusic){

  }

  static void tampilkanMusic(String tambahMusic){

  }

  static void tampilkanPlaylist(String tambahPlaylist){

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