import java.util.Scanner;

public class musicPlayer{

   String music[] = new String [1000];
   String playlist[][] = new String [100][4];

  static void tambahMusic(String judul,String artis,String durasi,String album){

  }

  static void tampilkanMusic(String tambahMusic){

  }

  static void editDataMusic(String tambahMusic){

  }

  static void hapusDataMusic(String tambahMusic){

  }

  static void tambahPlaylist(String music){

  }

  static void tampilkanPlaylist(String tambahPlaylist){

  }

  static void editDataPlaylist(String tambahPlaylist){

  }

  static void hapusDataPlaylist(String tambahPlaylist){

  }

  static void cariData(String tambahMusic){

  }

  static void play(String music){

  }

  static void shufflePlay(String rekomendasi){

  }

  static void rekomendasiMusic(String tampilkanPlaylist){
    
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