import java.util.Scanner;

public class musicPlayer{

  static Scanner input = new Scanner(System.in);

   String music[] = new String [1000];


  //  PlayList
   String playlist[] = new String[];
   int banyakPlaylist = 0;

  static void tambahMusic(String judul,String artis,String durasi,String album){

  }

  // Bagian Playlist

  static void tambahPlaylist(String music){
      if (banyakPlaylist <= 10){
        System.out.println("Playlist Penuh");
        return;
      } else {
        System.out.println("Masukkan nama playlist: ");
        String addNamaPlaylist = input.nextString();

        namaPlaylist[jumlahPlaylist] = nama;
        banyakPlaylist++;


      }




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
     

      int pilihan = 0;

      do{
        System.out.println("==ONLINE MUSIC PLAYER==");
        System.out.println("1. Lihat semua musik");
        System.out.println("2. Cari musik");
        System.out.println("3. Tambah musik");
        System.out.println("4. Hapus musik");
        pilihan = in.nextInt();

        if(pilihan == 1){
          musicList(music);
        }
        
      }
      while (pilihan != 0); {
        System.out.println("Keluar dari aplikasi");
      }

     
    
      


  }
}