import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  isUserCollapsed = false;
  isExcelCollapsed = false;
  isHistoryCollapsed = false;
  isAccountCollapsed = false;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  userscollapse() {
    if (this.isUserCollapsed === false) {
      this.isUserCollapsed = true;
    } else { this.isUserCollapsed = false; }
  }

  accountcollapse() {
    if (this.isAccountCollapsed === false) {
      this.isAccountCollapsed = true;
    } else { this.isAccountCollapsed = false; }
  }
	
	 excelcollapse() {
	    if (this.isExcelCollapsed === false) {
	      this.isExcelCollapsed = true;
	    } else { this.isExcelCollapsed = false; }
	  }
	
	 historycollapse() {
	    if (this.isHistoryCollapsed === false) {
	      this.isHistoryCollapsed = true;
	    } else { this.isHistoryCollapsed = false; }
	  }
	
	

}
