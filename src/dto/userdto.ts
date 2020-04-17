
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

    authorities: string[];

    constructor() {
        this.login = "";
        this.lastName = "string";
        this.firstName="string";
        this.email = "";
        this.id=0;

        this.imageUrl = "string";

        this.activated = true;


        this.langKey = "string";

        this.createdBy = "string";

        this.createdDate = "2020-04-16T15:41:03.999Z";

        this.lastModifiedBy = "sting";

        this.lastModifiedDate = "2020-04-16T15:41:03.999Z";
        this.authorities = new Array();
        this.authorities[0] = "";
    }
}

