import {Usertype} from './usertype';

/**
 * Classe DTO di User. DEVE essere uguale (stesso nome classe, stessi attributi e stessi nomi) a
 * quello nel backend. 
 * 
 * @see Usertype
 * 
 * @author Vittorio Valent
 */
export class UserDTO {

    id: number;

    login: string;

    
    firstName: string;

    
    lastName: string;

    email: string;

    
    imageUrl: string;

    activated: boolean;

    
    langKey: string;

    createdBy: string;

    createdDate: any;

    lastModifiedBy: string;

    lastModifiedDate: any;

    authorities: string;
}

