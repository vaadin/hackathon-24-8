import {i18n, key, translate} from '@vaadin/hilla-react-i18n';
import { Outlet, useLocation, useNavigate } from 'react-router';
import {
  AppLayout,
  Avatar,
  Icon,
  MenuBar,
  MenuBarItem,
  MenuBarItemSelectedEvent,
  ProgressBar,
  Scroller,
  SideNav,
  SideNavItem,
} from '@vaadin/react-components';
import { Suspense } from 'react';
import { createMenuItems } from '@vaadin/hilla-file-router/runtime.js';
i18n.configure();
function Header() {
  // TODO Replace with real application logo and name
  return (
    <div className="flex p-m gap-m items-center" slot="drawer">
      <Icon icon="vaadin:cubes" className="text-primary icon-l" />
      <span className="font-semibold text-l">{translate(key`span.element-0.text-content`)}</span>
    </div>
  );
}

function MainMenu() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <SideNav className="mx-m" onNavigate={({ path }) => path != null && navigate(path)} location={location}>
      {createMenuItems().map(({ to, icon, title }) => (
        <SideNavItem path={to} key={to}>
          {icon && <Icon icon={icon} slot="prefix" />}
          {title}
        </SideNavItem>
      ))}
    </SideNav>
  );
}

type UserMenuItem = MenuBarItem<{ action?: () => void }>;

function UserMenu() {
  // TODO Replace with real user information and actions
  const items: Array<UserMenuItem> = [
    {
      component: (
        <>
          <Avatar theme="xsmall" name="John Smith" colorIndex={5} className="mr-s" /> John Smith
        </>
      ),
      children: [
        { text: 'View Profile', disabled: true, action: () => console.log('View Profile') },
        { text: 'Manage Settings', disabled: true, action: () => console.log('Manage Settings') },
        { text: 'Logout', disabled: true, action: () => console.log('Logout') },
      ],
    },
  ];
  const onItemSelected = (event: MenuBarItemSelectedEvent<UserMenuItem>) => {
    event.detail.value.action?.();
  };
  return (
    <MenuBar theme="tertiary-inline" items={items} onItemSelected={onItemSelected} className="m-m" slot="drawer" />
  );
}

export default function MainLayout() {
  return (
      <Suspense fallback={<ProgressBar indeterminate={true} className="m-0" />}>
        <Outlet />
      </Suspense>
  );
}
