
/**
 * Classe DTO di User. DEVE essere uguale (stesso nome classe, stessi attributi e stessi nomi) a
 * quello nel backend. 
 * 
 * @see Usertype
 * 
 * @author Vittorio Valent
 */

import { ProductDTO } from '../dto/productdto';

export class ExcelDTO {

	directory: string;

	par1 : number;
	par2 : number;
	par3 : number;
	par4 : number;



	//non sappiamo se gli array potranno essere compatibili con la lista passata da spring
	productsList : ProductDTO[] = [];
	titleRead : string[] = [];
	
	addProduct(product: ProductDTO) {
        this.productsList.push(product);
	}
	
	addTitle(title: string) {
        this.titleRead.push(title);
	}
}

