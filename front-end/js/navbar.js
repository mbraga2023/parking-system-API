function createNavBar() {
    const nav = document.getElementById("navbar");
    const navFragment = document.createDocumentFragment();

    const navList = document.createElement("ul");
    const navItems = [
        { text: "Home", href: "index.html" },
        { text: "List", href: "userList.html" },
        { text: "Add", href: "register.html" },
    ];

    navItems.forEach((item) => {
        const navItem = document.createElement("li");
        const navLink = document.createElement("a");
        navLink.href = item.href;
        navLink.textContent = item.text;
        navItem.appendChild(navLink);
        navList.appendChild(navItem);
    });

    navFragment.appendChild(navList);
    nav.appendChild(navFragment);
}
createNavBar();
